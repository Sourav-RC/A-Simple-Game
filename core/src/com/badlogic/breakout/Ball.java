package com.badlogic.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;

    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update(Paddle paddle) {
        x += xSpeed;
        y += ySpeed;
        if (x < size || x > Gdx.graphics.getWidth() - size) {
            xSpeed = -xSpeed;
        }
        if (this.collidesWith(paddle) || y > Gdx.graphics.getHeight() - size) {
            ySpeed = -ySpeed;
        }
    }
    public void draw(ShapeRenderer shape) {
        shape.circle(x, y, size);
        shape.setColor(color);
    }
    public void checkCollision(Paddle paddle) {
        if(collidesWith(paddle)){
            color = Color.GREEN;
        }
        else {
            color = Color.WHITE;
        }
    }

    public void checkCollision(Block block) {
        if(collidesWith(block)){
            ySpeed = - ySpeed;
            block.destroyed = true;
        }
    }
    private boolean collidesWith(Paddle paddle) {
        if ((y - size) - (paddle.y + paddle.height) > 0
                || paddle.y - (y + size) > 0) {
            return false;
        }
        if ((x - size) - (paddle.x + paddle.width) > 0
                || paddle.x - (x + size) > 0) {
            return false;
        }
        return true;
    }

    private boolean collidesWith(Block block) {
        if ((y - size) - (block.y + block.height) > 0
                || block.y - (y + size) > 0) {
            return false;
        }
        if ((x - size) - (block.x + block.width) > 0
                || block.x - (x + size) > 0) {
            return false;
        }
        return true;
    }
}

