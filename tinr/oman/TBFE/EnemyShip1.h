//
//  EnemyShip1.h
//  TBFE
//
//  Created by Marko Oman on 10/25/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Retronator.TBFE.classes.h"
#import "Position.h"
#import "IParticle.h"

@interface EnemyShip1 : NSObject<IParticle> {
	Vector2 *position;
	Vector2 *velocity;
	float radius;
	float mass;
}

@end
