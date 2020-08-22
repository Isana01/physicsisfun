package com.example.myapplication;
import processing.core.PApplet;
import processing.core.PVector;

public class PendulumApplet extends PApplet {
    Pendulum p;
    public void setup() {
        // new Pendulum with an origin position and length
        p = new Pendulum(new PVector(width/2,0),275); //length hardcoded

    }

    public void draw() {
        background(255);
        p.go();
    }
    class Pendulum {
        PVector position;    // position of pendulum ball
        PVector origin;      // position of hook
        float len;             // Length
        float angle;         // Pendulum angle
        float aVelocity;     // Angle velocity
        float force;


        float damping;       // Arbitary damping amount

        // constructor
        Pendulum(PVector origin_, float len_) {
            // Fill all variables
            origin = origin_.get();
            position = new PVector();

            len = len_;
            angle = PI / 4;

            aVelocity = (float) 0.0;
            force = (float) 0.0;
            damping = (float) 0.995;   //air resistance cause damping
        }

        void go() {
            update();
            display();
        }


        void update() {
            float gravity = (float) 9.8;         // hardcoded
            force = (-1 * gravity / len) * sin(angle);  // for small angles the restoring force is f = -mg/l *sin angle
            aVelocity = aVelocity + force;                 // Increment  angular velocity
            aVelocity = aVelocity * damping;                       // damping
            angle = angle + aVelocity;                         // Increment angle
        }

        void display() {
            position.set(len * sin(angle), len * cos(angle), 0);  // Polar to cartesian conversion
            position.add(origin);

            stroke(0);
            strokeWeight(2);
            line(origin.x, origin.y, position.x, position.y);
            ellipseMode(CENTER);
            fill(175);
            ellipse(position.x, position.y, 60, 60);
        }
    }
}
