package com.brick.breaker.game.helper;

import static com.brick.breaker.game.helper.Constants.PPM;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyHelperService {

  public static Body createBody(float x, float y, float width, float height, boolean isStatic,
      World world) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(x / PPM, y / PPM);
    bodyDef.fixedRotation = true;
    Body body = world.createBody(bodyDef);
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(width / (2 * PPM), height / (2 * PPM));
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.friction = 0;
    fixtureDef.density = 100;
    body.createFixture(fixtureDef);
    shape.dispose();
    return body;
  }

  public static Body createBody(float x, float y, float radius, boolean isStatic, World world) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(x / PPM, y / PPM);
    bodyDef.fixedRotation = true;
    Body body = world.createBody(bodyDef);
    CircleShape shape = new CircleShape();
    shape.setRadius(radius / PPM);
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.friction = 0;
    body.createFixture(fixtureDef);
    shape.dispose();
    return body;
  }
}
