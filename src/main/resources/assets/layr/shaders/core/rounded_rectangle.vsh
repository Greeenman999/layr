#version 330

// Can't moj_import in things used during startup, when resource packs don't exist.
// This is a copy of dynamicimports.glsl and projection.glsl
layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
};
layout(std140) uniform Projection {
    mat4 ProjMat;
};


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
