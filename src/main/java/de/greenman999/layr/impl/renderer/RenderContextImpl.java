package de.greenman999.layr.impl.renderer;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import de.greenman999.layr.api.renderer.RenderContext;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.render.TextureSetup;
import net.minecraft.client.gui.render.state.GuiElementRenderState;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.lwjgl.system.MemoryUtil;

import java.awt.*;

public record RenderContextImpl(@NonNull GuiGraphics guiGraphics) implements RenderContext {

	//? if fabric {
	public static final VertexFormatElement WIDTH_HEIGHT_VERTEX_FORMAT_ELEMENT = VertexFormatElement.register(
			getNextVertexFormatId(),
			0,
			VertexFormatElement.Type.FLOAT,
			VertexFormatElement.Usage.GENERIC,
			2
	);

	public static final VertexFormatElement RADIUS_VERTEX_FORMAT_ELEMENT = VertexFormatElement.register(
			getNextVertexFormatId(),
			0,
			VertexFormatElement.Type.FLOAT,
			VertexFormatElement.Usage.GENERIC,
			4
	);

	public static final RenderPipeline ROUNDED_RECTANGLE_PIPELINE = RenderPipelines.register(
			RenderPipeline.builder(RenderPipelines.GUI_SNIPPET)
					.withLocation(Identifier.fromNamespaceAndPath("layr", "pipeline/rounded_rectangle"))
					.withVertexFormat(VertexFormat.builder()
							.add("Position", VertexFormatElement.POSITION)
							.add("Color", VertexFormatElement.COLOR)
							.add("UV0", VertexFormatElement.UV0)
							.add("WidthHeight", WIDTH_HEIGHT_VERTEX_FORMAT_ELEMENT)
							.add("Radius", RADIUS_VERTEX_FORMAT_ELEMENT)
							.build(), VertexFormat.Mode.QUADS)
					.withFragmentShader(Identifier.fromNamespaceAndPath("layr", "core/rounded_rectangle"))
					.withVertexShader(Identifier.fromNamespaceAndPath("layr", "core/rounded_rectangle"))
					.build()
	);

	private static int getNextVertexFormatId() {
		for (int vertexFormatIndex = 0; vertexFormatIndex < VertexFormatElement.MAX_COUNT; vertexFormatIndex++) {
			if (VertexFormatElement.byId(vertexFormatIndex) == null) return vertexFormatIndex;
		}
		throw new IllegalStateException("No free VertexFormatElement IDs available");
	}
	//? }

	@Override
	public void fillRect(int x, int y, int width, int height, Color color) {
		guiGraphics.fill(x, y, x + width, y + height, color.getRGB());
	}

	@Override
	public void fillRoundedRect(int x, int y, int width, int height, float radius, Color color) {
		//? if fabric {
		Matrix3x2fc pose = new Matrix3x2f(guiGraphics.pose());
		guiGraphics.guiRenderState.submitGuiElement(new GuiElementRenderState() {
			@Override
			public void buildVertices(@NonNull VertexConsumer consumer) {
				if (!(consumer instanceof BufferBuilder bufferBuilder)) return;
				consumer.addVertexWith2DPose(pose, x, y + height).setColor(color.getRGB()).setUv(0, 0);
				addWidthHeight(bufferBuilder, width, height);
				addRadius(bufferBuilder, radius);
				consumer.addVertexWith2DPose(pose, x + width, y + height).setColor(color.getRGB()).setUv(width, 0).setUv(radius, radius).setUv(radius, radius);
				addWidthHeight(bufferBuilder, width, height);
				addRadius(bufferBuilder, radius);
				consumer.addVertexWith2DPose(pose, x + width, y).setColor(color.getRGB()).setUv(width, height).setUv(radius, radius).setUv(radius, radius);
				addWidthHeight(bufferBuilder, width, height);
				addRadius(bufferBuilder, radius);
				consumer.addVertexWith2DPose(pose, x, y).setColor(color.getRGB()).setUv(0, height).setUv(radius, radius).setUv(radius, radius);
				addWidthHeight(bufferBuilder, width, height);
				addRadius(bufferBuilder, radius);
			}

			private void addWidthHeight(BufferBuilder bufferBuilder, int width, int height) {
				long pointer = bufferBuilder.beginElement(WIDTH_HEIGHT_VERTEX_FORMAT_ELEMENT);
				if (pointer != -1L) {
					MemoryUtil.memPutFloat(pointer, width);
					MemoryUtil.memPutFloat(pointer + 4L, height);
				}
			}

			private void addRadius(BufferBuilder bufferBuilder, float radius) {
				long pointer = bufferBuilder.beginElement(RADIUS_VERTEX_FORMAT_ELEMENT);
				if (pointer != -1L) {
					MemoryUtil.memPutFloat(pointer, radius);
					MemoryUtil.memPutFloat(pointer + 4L, radius);
					MemoryUtil.memPutFloat(pointer + 8L, radius);
					MemoryUtil.memPutFloat(pointer + 12L, radius);
				}
			}

			@Override
			public @NonNull RenderPipeline pipeline() {
				return ROUNDED_RECTANGLE_PIPELINE;
			}

			@Override
			public @NonNull TextureSetup textureSetup() {
				return TextureSetup.noTexture();
			}

			@Override
			public @Nullable ScreenRectangle scissorArea() {
				return guiGraphics.scissorStack.peek();
			}

			@Override
			public @Nullable ScreenRectangle bounds() {
				return getBounds(x, y, x + width, y + height, guiGraphics.pose(), scissorArea());
			}

			private static @Nullable ScreenRectangle getBounds(int x0, int y0, int x1, int y1, Matrix3x2fc pose, @Nullable ScreenRectangle scissorArea) {
				ScreenRectangle screenRectangle = (new ScreenRectangle(x0, y0, x1 - x0, y1 - y0)).transformMaxBounds(pose);
				return scissorArea != null ? scissorArea.intersection(screenRectangle) : screenRectangle;
			}
		});
		//? }
	}
}
