public static class Model_witch_14 extends EntityModel {
	private final RendererModel head;
	private final RendererModel nose;
	private final RendererModel hat;
	private final RendererModel hat2;
	private final RendererModel hat3;
	private final RendererModel hat4;
	private final RendererModel body;
	private final RendererModel arms;
	private final RendererModel leg0;
	private final RendererModel leg1;

	public Model_witch_14() {
		textureWidth = 64;
		textureHeight = 128;

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F, false));

		nose = new RendererModel(this);
		nose.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.addChild(nose);
		nose.cubeList.add(new ModelBox(nose, 24, 0, -1.0F, -27.0F, -6.0F, 2, 4, 2, 0.0F, false));
		nose.cubeList.add(new ModelBox(nose, 0, 0, 0.0F, -26.0F, -6.75F, 1, 1, 1, -0.25F, false));

		hat = new RendererModel(this);
		hat.setRotationPoint(-5.0F, -8.0313F, -5.0F);
		head.addChild(hat);
		hat.cubeList.add(new ModelBox(hat, 0, 64, 0.0F, -2.0187F, 0.0F, 10, 2, 10, 0.0F, false));

		hat2 = new RendererModel(this);
		hat2.setRotationPoint(6.75F, 0.0313F, 7.0F);
		hat.addChild(hat2);
		hat2.cubeList.add(new ModelBox(hat2, 0, 76, -5.0F, -5.5F, -5.0F, 7, 4, 7, 0.0F, false));

		hat3 = new RendererModel(this);
		hat3.setRotationPoint(0.0F, -3.0F, 0.0F);
		hat2.addChild(hat3);
		hat3.cubeList.add(new ModelBox(hat3, 0, 87, -3.25F, -5.5F, -3.0F, 4, 4, 4, 0.0F, false));

		hat4 = new RendererModel(this);
		hat4.setRotationPoint(0.0F, -3.0F, 0.0F);
		hat3.addChild(hat4);
		hat4.cubeList.add(new ModelBox(hat4, 0, 95, -1.5F, -4.0F, -1.0F, 1, 2, 1, 0.25F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 20, -4.0F, -24.0F, -3.0F, 8, 12, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 38, -4.0F, -24.0F, -3.0F, 8, 18, 6, 0.5F, false));

		arms = new RendererModel(this);
		arms.setRotationPoint(0.0F, 2.0F, 0.0F);
		setRotationAngle(arms, -0.7854F, 0.0F, 0.0F);
		arms.cubeList.add(new ModelBox(arms, 40, 38, -4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F, false));
		arms.cubeList.add(new ModelBox(arms, 44, 22, -8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));
		arms.cubeList.add(new ModelBox(arms, 44, 22, 4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));

		leg0 = new RendererModel(this);
		leg0.setRotationPoint(-2.0F, 12.0F, 0.0F);
		leg0.cubeList.add(new ModelBox(leg0, 0, 22, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		leg1 = new RendererModel(this);
		leg1.setRotationPoint(2.0F, 12.0F, 0.0F);
		leg1.cubeList.add(new ModelBox(leg1, 0, 22, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		arms.render(f5);
		leg0.render(f5);
		leg1.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}