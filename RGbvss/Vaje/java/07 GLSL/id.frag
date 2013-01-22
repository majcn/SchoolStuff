out vec4 vFragColor;	// Fragment color to rasterize
in vec4 vVaryingColor;	// Incoming color from vertex stage

void main(void) {
	vFragColor = vVaryingColor;	// Interpolated color to fragment
}