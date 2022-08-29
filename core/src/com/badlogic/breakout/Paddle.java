package com.badlogic.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y = 10;
    int width = 100;
    int height = 15;
    Color color = Color.WHITE;

    public Paddle(){
        x = (Gdx.graphics.getWidth() - width)/2;
    }

    public void update(int x){
        if (x < 0) {
            this.x = 0;
        }
        else if (x > Gdx.graphics.getWidth() - width){
            this.x = Gdx.graphics.getWidth() - width;
        }
        else {
            this.x = x;
        }
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x, y, width , height);
        shape.setColor(color);
    }

    public void checkCollision(Ball ball) {
        if(collidesWith(ball)){
            color =Color.GREEN;
        }
        else {
            color = Color.WHITE;
        }
    }

    private boolean collidesWith(Ball ball) {
        if ((ball.y - ball.size) - (y + height) > 0
                || y - (ball.y + ball.size) > 0) {
            return false;
        }
        if ((ball.x - ball.size) - (x + width) > 0
                || x - (ball.x + ball.size) > 0) {
            return false;
        }
        return true;
    }
}
