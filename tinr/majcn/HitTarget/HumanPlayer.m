//
//  HumanPlayer.m
//  HitTarget
//
//  Created by Gregor Majcen on 12/7/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "HumanPlayer.h"
#import "majcn.HitTarget.h"

@implementation HumanPlayer

- (id) initWithGame:(Game*)theGame Archer:(Archer*)theArcher Arrow:(Arrow*)theArrow;
{	
	self = [super initWithGame:theGame Archer:theArcher Arrow:theArrow];
	sound = [theGame.content load:@"INCOMING"];
	pressedPosition = [[Vector2 alloc] init];
	return self;
}

- (void) updateWithGameTime:(GameTime *)gameTime
{
	TouchCollection *touches = [TouchPanelHelper getState];
	for(TouchLocation *touch in touches)
	{		
		if(touch.state == TouchLocationStatePressed)
		{
			[pressedPosition set:touch.position];
			[archer.fire.position set:touch.position];
		}
		else if (touch.state == TouchLocationStateMoved)
		{
			if(!archer.launched)
			{
				archer.rotationAngle = -acosf((pressedPosition.x - touch.position.x)/([[Vector2 subtract:touch.position by:pressedPosition] length]));
				archer.power = [[Vector2 subtract:pressedPosition by:touch.position] length];
				if(archer.power > 50)
					archer.power = 50;
			}
		}
		else if (touch.state == TouchLocationStateReleased)
		{
			if(!archer.launched)
			{
				arrow.rotationAngle = archer.rotationAngle;
				arrow.velocity = [Vector2 subtract:pressedPosition by:touch.position];
				if([arrow.velocity length] > 50)
					[[arrow.velocity normalize] multiplyBy:50];
				[arrow.velocity multiplyBy:4];
				[sound play];
			}
			archer.launched = YES;
		}
	}
}

- (void) dealloc
{
	[pressedPosition release];
	[super dealloc];
}


@end
