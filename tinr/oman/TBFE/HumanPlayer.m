//
//  HumanPlayer.m
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Retronator.Xni.Framework.Input.Touch.h"

#import "Retronator.TBFE.h"
#import "HumanPlayer.h"

@implementation HumanPlayer

- (id) initWithShip:(Ship *)theShip scene:(id <IScene>)theScene position:(PlayerPosition)thePosition game:(Game*)game
{
	self = [super initWithShip:theShip scene:theScene position:thePosition];
	if (self != nil) {
		inputArea = [[Rectangle alloc] initWithRectangle:game.window.clientBounds];
		inputArea.height = 320;
		inputArea.y = game.window.clientBounds.height - inputArea.height;
		
		touchOffset = [[Vector2 alloc] initWithX:0 y: -40];
	}
	return self;
}

- (void) updateWithGameTime:(GameTime *)gameTime {
	TouchCollection *touches = [[TouchPanel instance] getState];
	
	// Remember old position for velocity calculation.
	//Vector2 *oldPosition = [Vector2 vectorWithVector:ship.position]; 
	
	
	BOOL touchesInInputArea = NO;
	for (TouchLocation *touch in touches) {
		if ([inputArea containsVector:touch.position]) {
			touchesInInputArea = YES;
			
			if (!grabbed) {
				float distanceToShip = [[[Vector2 subtract:touch.position by:ship.position] add:touchOffset] length];
				if (distanceToShip < 50) {
					grabbed = YES;
				}
			}
			
			if (grabbed) {
				[[ship.position set:touch.position] add:touchOffset];
				
			}
		}
	}
	
	if (!touchesInInputArea) {
		grabbed = NO;
	}
	
	// Calculate mallet velocity in reverse.
	//Vector2 *distance = [Vector2 subtract:ship.position by:oldPosition];
	
	// Velocity is distance over time
	//if (gameTime.elapsedGameTime > 0) {
	//	[ship.velocity set:[distance multiplyBy:1.0f/gameTime.elapsedGameTime]];	
	//}
	
	NSLog(@"%@", ship.velocity);
}

- (void) dealloc
{
	[inputArea release];
	[super dealloc];
}


@end

