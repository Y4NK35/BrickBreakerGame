package com.brick.breaker.game.helper;

import static com.brick.breaker.game.helper.Constants.BAR_HEIGHT;
import static com.brick.breaker.game.helper.Constants.BRICK_HEIGHT;
import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.brick.breaker.game.objects.Ball;
import com.brick.breaker.game.objects.Bar;
import com.brick.breaker.game.objects.Brick;

public class MyContactListener implements ContactListener {

  @Override
  public void beginContact(Contact contact) {
    Fixture fa = contact.getFixtureA();
    Fixture fb = contact.getFixtureB();
    if (fa == null || fb == null) {
      return;
    }
    if (fa.getBody().getUserData() == null || fb.getBody().getUserData() == null) {
      return;
    }

    if (isBallBrickContact(fa, fb)) {
      if (fa.getBody().getUserData() instanceof Ball) {
        ((Brick) fb.getBody().getUserData()).setDestroyed(true);
        if (isSideBrickContact(fa, fb)) {
          ((Ball) fa.getBody().getUserData()).setVelX(-1);
        } else {
          ((Ball) fa.getBody().getUserData()).setVelY(-1);
        }
      } else {
        ((Brick) fa.getBody().getUserData()).setDestroyed(true);
        if ((isSideBrickContact(fb, fa))) {
          ((Ball) fb.getBody().getUserData()).setVelX(-1);
        } else {
          ((Ball) fb.getBody().getUserData()).setVelY(-1);
        }
      }
    }

    if (isBallBarContact(fa, fb)) {
      if (fa.getBody().getUserData() instanceof Ball) {
        float ballVelX = calculateBallXFactor(((Ball) fa.getBody().getUserData()).getVelX(),
            ((Bar) fb.getBody().getUserData()).getVelX());
        ((Ball) fa.getBody().getUserData()).setVelXFactor(1 + ballVelX);
        if (isSideBarContact(fa, fb)) {
          ((Ball) fa.getBody().getUserData()).setVelX(-1);
        } else {
          ((Ball) fa.getBody().getUserData()).setVelX(ballVelX);
          ((Ball) fa.getBody().getUserData()).setVelY(-1);
        }
      } else {
        float ballVelX = calculateBallXFactor(((Ball) fb.getBody().getUserData()).getVelX(),
            ((Bar) fa.getBody().getUserData()).getVelX());
        ((Ball) fb.getBody().getUserData()).setVelXFactor(1 + ballVelX);
        if ((isSideBarContact(fb, fa))) {
          ((Ball) fb.getBody().getUserData()).setVelX(-1);
        } else {
          ((Ball) fb.getBody().getUserData()).setVelY(-1);
        }
      }
    }
  }

  @Override
  public void endContact(Contact contact) {
  }

  @Override
  public void preSolve(Contact contact, Manifold manifold) {
  }

  @Override
  public void postSolve(Contact contact, ContactImpulse contactImpulse) {
  }

  public boolean isBallBrickContact(Fixture fa, Fixture fb) {
    if (fa.getBody().getUserData() instanceof Ball && fb.getBody().getUserData() instanceof Brick
        || fa.getBody().getUserData() instanceof Brick && fb.getBody()
        .getUserData() instanceof Ball) {
      return true;
    }
    return false;
  }

  public boolean isBallBarContact(Fixture fa, Fixture fb) {
    if (fa.getBody().getUserData() instanceof Ball && fb.getBody().getUserData() instanceof Bar
        || fa.getBody().getUserData() instanceof Bar && fb.getBody()
        .getUserData() instanceof Ball) {
      return true;
    }
    return false;
  }

  public boolean isSideBrickContact(Fixture fa, Fixture fb) {
    return ((fb.getBody().getPosition().y - (BRICK_HEIGHT ) / (PPM*2)) < fa.getBody()
        .getPosition().y
        && (fb.getBody().getPosition().y + (BRICK_HEIGHT ) / (PPM * 2)) > fa.getBody()
        .getPosition().y);
  }

  public boolean isSideBarContact(Fixture fa, Fixture fb) {
    return (
        (fb.getBody().getPosition().y - (BAR_HEIGHT -1) / (PPM * 2)) < fa.getBody().getPosition().y
            && (fb.getBody().getPosition().y + (BAR_HEIGHT-1) / (PPM * 2)) > fa.getBody()
            .getPosition().y);
  }

  public float calculateBallXFactor(float velXBall, float velXBar) {
    if (velXBall > 0) {
      return (velXBar / 5);
    } else {
      return -velXBar / 5;
    }
  }
}
