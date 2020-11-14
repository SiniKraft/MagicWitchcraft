public static class Model_dangerous_dragon_14 extends EntityModel {
	private final RendererModel neck;
	private final RendererModel neck2;
	private final RendererModel neck3;
	private final RendererModel neck4;
	private final RendererModel neck5;
	private final RendererModel head;
	private final RendererModel jaw;
	private final RendererModel body;
	private final RendererModel wing;
	private final RendererModel wingtip;
	private final RendererModel wing1;
	private final RendererModel wingtip1;
	private final RendererModel rearleg;
	private final RendererModel rearlegtip;
	private final RendererModel rearfoot;
	private final RendererModel rearleg1;
	private final RendererModel rearlegtip1;
	private final RendererModel rearfoot1;
	private final RendererModel frontleg;
	private final RendererModel frontlegtip;
	private final RendererModel frontfoot;
	private final RendererModel frontleg1;
	private final RendererModel frontlegtip1;
	private final RendererModel frontfoot1;
	private final RendererModel tail;
	private final RendererModel tail2;
	private final RendererModel tail3;
	private final RendererModel tail4;
	private final RendererModel tail5;
	private final RendererModel tail6;
	private final RendererModel tail7;
	private final RendererModel tail8;
	private final RendererModel tail9;
	private final RendererModel tail10;
	private final RendererModel tail11;
	private final RendererModel tail12;

	public Model_dangerous_dragon_14() {
		textureWidth = 256;
		textureHeight = 256;

		neck = new RendererModel(this);
		neck.setRotationPoint(0.0F, 17.0F, -8.0F);
		neck.cubeList.add(new ModelBox(neck, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
		neck.cubeList.add(new ModelBox(neck, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));

		neck2 = new RendererModel(this);
		neck2.setRotationPoint(0.0F, 0.0F, -10.0F);
		neck.addChild(neck2);
		neck2.cubeList.add(new ModelBox(neck2, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
		neck2.cubeList.add(new ModelBox(neck2, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));

		neck3 = new RendererModel(this);
		neck3.setRotationPoint(0.0F, 0.0F, -10.0F);
		neck2.addChild(neck3);
		neck3.cubeList.add(new ModelBox(neck3, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
		neck3.cubeList.add(new ModelBox(neck3, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));

		neck4 = new RendererModel(this);
		neck4.setRotationPoint(0.0F, 0.0F, -10.0F);
		neck3.addChild(neck4);
		neck4.cubeList.add(new ModelBox(neck4, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
		neck4.cubeList.add(new ModelBox(neck4, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));

		neck5 = new RendererModel(this);
		neck5.setRotationPoint(0.0F, 0.0F, -10.0F);
		neck4.addChild(neck5);
		neck5.cubeList.add(new ModelBox(neck5, 192, 104, -5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F, false));
		neck5.cubeList.add(new ModelBox(neck5, 48, 0, -1.0F, -9.0F, -8.0F, 2, 4, 6, 0.0F, false));

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, 0.0F, -10.0F);
		neck5.addChild(head);
		head.cubeList.add(new ModelBox(head, 176, 44, -6.0F, -1.0F, -30.0F, 12, 5, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 112, 30, -8.0F, -8.0F, -16.0F, 16, 16, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 0, -5.0F, -12.0F, -10.0F, 2, 4, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 112, 0, -5.0F, -3.0F, -28.0F, 2, 2, 4, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 0, 0, 3.0F, -12.0F, -10.0F, 2, 4, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 112, 0, 3.0F, -3.0F, -28.0F, 2, 2, 4, 0.0F, false));

		jaw = new RendererModel(this);
		jaw.setRotationPoint(0.0F, 4.0F, -13.0F);
		head.addChild(jaw);
		setRotationAngle(jaw, 0.2618F, 0.0F, 0.0F);
		jaw.cubeList.add(new ModelBox(jaw, 176, 65, -6.0F, 0.0F, -17.0F, 12, 4, 16, 0.0F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 4.0F, 8.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -12.0F, 0.0F, -16.0F, 24, 24, 64, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, -10.0F, 2, 6, 12, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, 10.0F, 2, 6, 12, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 220, 53, -1.0F, -6.0F, 30.0F, 2, 6, 12, 0.0F, false));

		wing = new RendererModel(this);
		wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
		wing.cubeList.add(new ModelBox(wing, 112, 88, -56.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, false));
		wing.cubeList.add(new ModelBox(wing, -56, 88, -56.0F, 0.0F, 2.0F, 56, 0, 56, 0.01F, false));

		wingtip = new RendererModel(this);
		wingtip.setRotationPoint(-56.0F, 0.0F, -2.0F);
		wing.addChild(wingtip);
		wingtip.cubeList.add(new ModelBox(wingtip, 112, 136, -56.0F, -2.0F, 0.0F, 56, 4, 4, 0.0F, false));
		wingtip.cubeList.add(new ModelBox(wingtip, -56, 144, -56.0F, 0.0F, 4.0F, 56, 0, 56, 0.01F, false));

		wing1 = new RendererModel(this);
		wing1.setRotationPoint(12.0F, 5.0F, 2.0F);
		wing1.cubeList.add(new ModelBox(wing1, 112, 88, 0.0F, -4.0F, -4.0F, 56, 8, 8, 0.0F, true));
		wing1.cubeList.add(new ModelBox(wing1, -56, 88, 0.0F, 0.0F, 2.0F, 56, 0, 56, 0.01F, true));

		wingtip1 = new RendererModel(this);
		wingtip1.setRotationPoint(56.0F, 0.0F, -2.0F);
		wing1.addChild(wingtip1);
		wingtip1.cubeList.add(new ModelBox(wingtip1, 112, 136, 0.0F, -2.0F, 0.0F, 56, 4, 4, 0.0F, true));
		wingtip1.cubeList.add(new ModelBox(wingtip1, -56, 144, 0.0F, 0.0F, 4.0F, 56, 0, 56, 0.01F, true));

		rearleg = new RendererModel(this);
		rearleg.setRotationPoint(-16.0F, 16.0F, 42.0F);
		setRotationAngle(rearleg, 1.5708F, 0.0F, 0.0F);
		rearleg.cubeList.add(new ModelBox(rearleg, 0, 0, -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, false));

		rearlegtip = new RendererModel(this);
		rearlegtip.setRotationPoint(0.0F, 28.0F, 1.0F);
		rearleg.addChild(rearlegtip);
		rearlegtip.cubeList.add(new ModelBox(rearlegtip, 196, 0, -6.0F, 0.0F, -7.0F, 12, 32, 12, 0.0F, false));

		rearfoot = new RendererModel(this);
		rearfoot.setRotationPoint(0.0F, 32.0F, -2.0F);
		rearlegtip.addChild(rearfoot);
		setRotationAngle(rearfoot, 1.5708F, 0.0F, 0.0F);
		rearfoot.cubeList.add(new ModelBox(rearfoot, 112, 0, -9.0F, 0.0F, -20.0F, 18, 6, 24, 0.0F, false));

		rearleg1 = new RendererModel(this);
		rearleg1.setRotationPoint(16.0F, 16.0F, 42.0F);
		setRotationAngle(rearleg1, 1.5708F, 0.0F, 0.0F);
		rearleg1.cubeList.add(new ModelBox(rearleg1, 0, 0, -8.0F, -4.0F, -8.0F, 16, 32, 16, 0.0F, true));

		rearlegtip1 = new RendererModel(this);
		rearlegtip1.setRotationPoint(0.0F, 28.0F, 1.0F);
		rearleg1.addChild(rearlegtip1);
		rearlegtip1.cubeList.add(new ModelBox(rearlegtip1, 196, 0, -6.0F, 0.0F, -7.0F, 12, 32, 12, 0.0F, true));

		rearfoot1 = new RendererModel(this);
		rearfoot1.setRotationPoint(0.0F, 32.0F, -2.0F);
		rearlegtip1.addChild(rearfoot1);
		setRotationAngle(rearfoot1, 1.5708F, 0.0F, 0.0F);
		rearfoot1.cubeList.add(new ModelBox(rearfoot1, 112, 0, -9.0F, 0.0F, -23.0F, 18, 6, 24, 0.0F, true));

		frontleg = new RendererModel(this);
		frontleg.setRotationPoint(-12.0F, 20.0F, 2.0F);
		setRotationAngle(frontleg, 1.1345F, 0.0F, 0.0F);
		frontleg.cubeList.add(new ModelBox(frontleg, 112, 104, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));

		frontlegtip = new RendererModel(this);
		frontlegtip.setRotationPoint(0.0F, 20.0F, 0.0F);
		frontleg.addChild(frontlegtip);
		setRotationAngle(frontlegtip, -0.3491F, 0.0F, 0.0F);
		frontlegtip.cubeList.add(new ModelBox(frontlegtip, 226, 138, -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, false));

		frontfoot = new RendererModel(this);
		frontfoot.setRotationPoint(0.0F, 22.0F, 0.0F);
		frontlegtip.addChild(frontfoot);
		setRotationAngle(frontfoot, 0.7854F, 0.0F, 0.0F);
		frontfoot.cubeList.add(new ModelBox(frontfoot, 144, 104, -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, false));

		frontleg1 = new RendererModel(this);
		frontleg1.setRotationPoint(12.0F, 20.0F, 2.0F);
		setRotationAngle(frontleg1, 1.1345F, 0.0F, 0.0F);
		frontleg1.cubeList.add(new ModelBox(frontleg1, 112, 104, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, true));

		frontlegtip1 = new RendererModel(this);
		frontlegtip1.setRotationPoint(0.0F, 20.0F, 0.0F);
		frontleg1.addChild(frontlegtip1);
		setRotationAngle(frontlegtip1, -0.3491F, 0.0F, 0.0F);
		frontlegtip1.cubeList.add(new ModelBox(frontlegtip1, 226, 138, -3.0F, -1.0F, -3.0F, 6, 24, 6, 0.0F, true));

		frontfoot1 = new RendererModel(this);
		frontfoot1.setRotationPoint(0.0F, 22.0F, 0.0F);
		frontlegtip1.addChild(frontfoot1);
		setRotationAngle(frontfoot1, 0.7854F, 0.0F, 0.0F);
		frontfoot1.cubeList.add(new ModelBox(frontfoot1, 144, 104, -4.0F, 0.0F, -12.0F, 8, 4, 16, 0.0F, true));

		tail = new RendererModel(this);
		tail.setRotationPoint(0.0F, 10.0F, 58.0F);
		tail.cubeList.add(new ModelBox(tail, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail.cubeList.add(new ModelBox(tail, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail2 = new RendererModel(this);
		tail2.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail2.cubeList.add(new ModelBox(tail2, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail3 = new RendererModel(this);
		tail3.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail2.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail3.cubeList.add(new ModelBox(tail3, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail4 = new RendererModel(this);
		tail4.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail3.addChild(tail4);
		tail4.cubeList.add(new ModelBox(tail4, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail4.cubeList.add(new ModelBox(tail4, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail5 = new RendererModel(this);
		tail5.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail4.addChild(tail5);
		tail5.cubeList.add(new ModelBox(tail5, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail5.cubeList.add(new ModelBox(tail5, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail6 = new RendererModel(this);
		tail6.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail5.addChild(tail6);
		tail6.cubeList.add(new ModelBox(tail6, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail6.cubeList.add(new ModelBox(tail6, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail7 = new RendererModel(this);
		tail7.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail6.addChild(tail7);
		tail7.cubeList.add(new ModelBox(tail7, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail7.cubeList.add(new ModelBox(tail7, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail8 = new RendererModel(this);
		tail8.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail7.addChild(tail8);
		tail8.cubeList.add(new ModelBox(tail8, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail8.cubeList.add(new ModelBox(tail8, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail9 = new RendererModel(this);
		tail9.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail8.addChild(tail9);
		tail9.cubeList.add(new ModelBox(tail9, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail9.cubeList.add(new ModelBox(tail9, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail10 = new RendererModel(this);
		tail10.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail9.addChild(tail10);
		tail10.cubeList.add(new ModelBox(tail10, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail10.cubeList.add(new ModelBox(tail10, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail11 = new RendererModel(this);
		tail11.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail10.addChild(tail11);
		tail11.cubeList.add(new ModelBox(tail11, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail11.cubeList.add(new ModelBox(tail11, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));

		tail12 = new RendererModel(this);
		tail12.setRotationPoint(0.0F, 0.0F, 10.0F);
		tail11.addChild(tail12);
		tail12.cubeList.add(new ModelBox(tail12, 192, 104, -5.0F, -5.0F, 0.0F, 10, 10, 10, 0.0F, false));
		tail12.cubeList.add(new ModelBox(tail12, 48, 0, -1.0F, -9.0F, 2.0F, 2, 4, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		neck.render(f5);
		body.render(f5);
		wing.render(f5);
		wing1.render(f5);
		rearleg.render(f5);
		rearleg1.render(f5);
		frontleg.render(f5);
		frontleg1.render(f5);
		tail.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}