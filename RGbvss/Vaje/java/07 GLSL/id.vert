in vec4 vVertex;	// Vertex position attribute
in vec4 vColor;		// Vertex color attribute

out vec4 vVaryingColor;	// Color value passed to fragment shader

void main(void) {
	vVaryingColor = vColor;	// Simply copy the color value
	gl_Position = vVertex;	// Simply pass along the vertex position
}