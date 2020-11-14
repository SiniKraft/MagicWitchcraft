public static class Model_magic_carpet_115 extends EntityModel<Entity> {
	private final ModelRenderer carpet;

	public Model_magic_carpet_115() {
		textureWidth = 16;
		textureHeight = 16;

		carpet = new ModelRenderer(this);
		carpet.setRotationPoint(0.0F, 24.0F, 0.0F);
		carpet.setTextureOffset(0, 0).addBox(-11.0F, -1.0F, -18.0F, 22.0F, 1.0F, 34.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		carpet.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}