public static class Model_magic_carpet_114 extends EntityModel {
	private final RendererModel carpet;

	public Model_magic_carpet_114() {
		textureWidth = 16;
		textureHeight = 16;

		carpet = new RendererModel(this);
		carpet.setRotationPoint(0.0F, 24.0F, 0.0F);
		carpet.cubeList.add(new ModelBox(carpet, 0, 0, -11.0F, -1.0F, -18.0F, 22, 1, 34, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		carpet.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}