#version 330

#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in vec2 WidthHeight;
in vec4 Radius;

out vec4 vertexColor;
out vec4 radius;
out vec2 texCoord0;
out vec2 widthHeight;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    vertexColor = Color;
    radius = Radius;
    texCoord0 = UV0;
    widthHeight = WidthHeight;
}
