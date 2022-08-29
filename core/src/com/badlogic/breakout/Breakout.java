package com.badlogic.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Random;

public class Breakout extends ApplicationAdapter {
	ShapeRenderer shape;
	ArrayList<Block> blocks = new ArrayList<>();

	Paddle paddle;
	Ball ball;
	Random r = new Random();

	@Override
	public void create () {
		shape = new ShapeRenderer();
		paddle = new Paddle();
		ball = new Ball(r.nextInt(Gdx.graphics.getWidth()),
				r.nextInt(Gdx.graphics.getHeight()/2), 10, 3, 3);

		int blockWidth = 63;
		int blockHeight = 20;
		for (int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
			for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
				blocks.add(new Block(x, y, blockWidth, blockHeight));
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);

		paddle.update(Gdx.input.getX());
		ball.update(paddle);

		paddle.draw(shape);
		ball.draw(shape);
		for(Block block : blocks){
			block.draw(shape);
			ball.checkCollision(block);
		}
		for (int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			if (b.destroyed) {
				blocks.remove(b);
				// we need to decrement i when a ball gets removed, otherwise we skip a ball!
				i--;
			}
		}
		paddle.checkCollision(ball);
		ball.checkCollision(paddle);

		shape.end();
	}
}