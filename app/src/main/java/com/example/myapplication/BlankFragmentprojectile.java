package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import processing.android.PFragment;
import processing.core.PApplet;
import processing.core.PVector;


public class BlankFragmentprojectile extends PApplet {

    float angle = -PI/4;
    PVector position = new PVector(50, 300);
    boolean shot = false;

    CannonBall ball;

    public void setup() {
        ball = new CannonBall(position.x, position.y);
    }

    public void draw() {
        background(255);

        pushMatrix();
        translate(position.x, position.y);
        rotate(angle);
        rect(0, -5, 100, 20);
        popMatrix();

        if (shot) {
            PVector gravity = new PVector(0, (float)0.2);
            ball.applyForce(gravity);
            ball.update();
        }
        ball.display(this);

        if (ball.position.y > height) {
            ball = new CannonBall(position.x, position.y);
            shot = false;
        }
    }

    public void keyPressed() {
        if (key == 'a' || key == 'A') {
            angle += 0.1;
        }
        else if (key == 'b' || key == 'B') {
            angle -= 0.1;
        }
        else if (mousePressed == true) {
            shot = true;
            PVector force = PVector.fromAngle(angle);
            force.mult(10);
            ball.applyForce(force);
        }
    }
}

class CannonBall {
    // All of our regular motion stuff
    PVector position;
    PVector velocity;
    PVector acceleration;



    float topspeed = 10;

    CannonBall(float x, float y) {
        position = new PVector(x,y);
        velocity = new PVector();
        acceleration = new PVector();
    }

    // Standard Euler integration
    void update() {
        velocity.add(acceleration);
        velocity.limit(topspeed);
        position.add(velocity);
        acceleration.mult(0);
    }

    void applyForce(PVector force) {
        acceleration.add(force);
    }


    public void display(BlankFragmentprojectile fg) {
        fg.stroke(0);
        fg.strokeWeight(2);
        fg.pushMatrix();
        fg.translate(position.x,position.y);
        fg.ellipse(0,0,32,32);
        fg.popMatrix();
    }

}