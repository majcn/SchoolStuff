//
//  Computer.m
//  TBFE
//
//  Created by Marko Oman on 11/11/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Computer.h"
#import "Retronator.TBFE.h"


@implementation Computer
- (id) initWithEnemyShip:(EnemyShip *)theEnemyShip scene:(id <IScene>)theScene position:(PlayerPosition)thePosition game:(Game*)game
{
	self = [super initWithEnemyShip:theEnemyShip scene:theScene position:thePosition];
	if (self != nil) {
				
	}
	return self;
}

- (void) updateWithGameTime:(GameTime *)gameTime {
	
	
	// Remember old position for velocity calculation.
	//Vector2 *oldPosition = [Vector2 vectorWithVector:enemyShip.position]; 
	
		
	// Calculate mallet velocity in reverse.
	//Vector2 *distance = [Vector2 subtract:enemyShip.position by:oldPosition];
	
	// Velocity is distance over time
	//if (gameTime.elapsedGameTime > 0) {
		//[enemyShip.velocity set:[distance multiplyBy:2.0f/gameTime.elapsedGameTime]];
		[enemyShip.position add:enemyShip.velocity];
			
	//}
	printf("lala");
	NSLog(@"%@", enemyShip.velocity);
}

- (void) dealloc
{
	
	[super dealloc];
}


@end
