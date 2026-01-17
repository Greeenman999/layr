#version 330

#moj_import <minecraft:dynamictransforms.glsl>

in vec4 v_color;
in vec4 v_cornerRadii;
in vec2 v_uv;
in vec2 v_size;

out vec4 fragColor;

float signedDistanceRoundedRect(vec2 point, vec2 halfExtents, vec4 cornerRadii) {
    cornerRadii.xy = (point.x > 0.0) ? cornerRadii.xy : cornerRadii.zw;
    cornerRadii.x  = (point.y > 0.0) ? cornerRadii.x  : cornerRadii.y;
    vec2 q = abs(point) - halfExtents + cornerRadii.x;
    return min(max(q.x,q.y), 0.0) + length(max(q, 0.0)) - cornerRadii.x;
}

void main() {
    vec2 halfSize = v_size / 2.0;
    float signedDistance = signedDistanceRoundedRect(v_uv - halfSize, halfSize, v_cornerRadii);
    float antialiasWidth = fwidth(signedDistance);
    float smoothed = smoothstep(0.0, antialiasWidth, signedDistance);
    float alpha = 1 - smoothed;
    vec4 color = ColorModulator * v_color * vec4(1.0, 1.0, 1.0, alpha);
    if (color.a == 0.0) discard;
    fragColor = color;
}
