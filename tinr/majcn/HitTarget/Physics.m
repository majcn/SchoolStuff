//
//  Physics.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Physics.h"
#import "majcn.HitTarget.h"

@implementation Physics

- (id) initWithGame:(Game *)theGame level:(Level *)theLevel
{
	if (self = [super initWithGame:theGame])
	{
		level = theLevel;
	}
	return self;
}

- (void) updateWithGameTime:(GameTime *)gameTime
{
	if([level.arrow.velocity length] != 0)
	{
		level.arrow.rotationAngle = atan2f(level.arrow.velocity.y, level.arrow.velocity.x); //carska zadeva :D
		[level.arrow.velocity add:[Vector2 multiply:[Vector2 vectorWithX:0 y:100] by:gameTime.elapsedGameTime]];
		[level.arrow.position add:[Vector2 multiply:level.arrow.velocity by:gameTime.elapsedGameTime]];
	}
}
@end
