//
//  EnemyShip.h
//  TBFE
//
//  Created by Marko Oman on 10/23/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Retronator.TBFE.classes.h"
#import "Position.h"
#import "IParticle.h"

@interface EnemyShip : NSObject<IParticle, IVelocity> {
	Vector2 *position;
	Vector2 *velocity;
	float radius;
	float mass;
}

@end
