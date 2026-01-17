#version 330

// Can't moj_import in things used during startup, when resource packs don't exist.
// This is a copy of dynamicimports.glsl
layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
};

in vec4 vertexColor;
in vec4 radius;
in vec2 texCoord0;
in vec2 widthHeight;

out vec4 fragColor;

float sdRoundBox(vec2 p, vec2 b, vec4 r) {
    r.xy = (p.x > 0.0) ? r.xy : r.zw;
    r.x  = (p.y > 0.0) ? r.x  : r.y;
    vec2 q = abs(p) - b + r.x;
    return min(max(q.x,q.y), 0.0) + length(max(q, 0.0)) - r.x;
}

void main() {
    float distance = sdRoundBox(texCoord0 - widthHeight / 2, widthHeight / 2, radius);
    float fw = fwidth(distance);
    distance = smoothstep(0, fw, distance);
    float alpha = 1-distance;
    vec4 color = ColorModulator * vertexColor * vec4(1, 1, 1, alpha);
    if (color.a == 0) discard;
    fragColor = color;
}
