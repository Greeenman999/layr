#version 330

#moj_import <minecraft:dynamictransforms.glsl>
#moj_import <minecraft:projection.glsl>

in vec3 a_pos;
in vec4 a_color;
in vec2 a_uv0;
in vec2 a_size;
in vec4 a_cornerRadii;

out vec4 v_color;
out vec4 v_cornerRadii;
out vec2 v_uv;
out vec2 v_size;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(a_pos, 1.0);

    v_color = a_color;
    v_cornerRadii = a_cornerRadii;
    v_uv = a_uv0;
    v_size = a_size;
}
