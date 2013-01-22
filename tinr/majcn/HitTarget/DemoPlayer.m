//
//  DemoPlayer.m
//  HitTarget
//
//  Created by Gregor Majcen on 12/7/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "DemoPlayer.h"
#import "majcn.HitTarget.h"

@implementation DemoPlayer

- (id) initWithGame:(Game*)theGame Archer:(Archer*)theArcher Arrow:(Arrow*)theArrow;
{	
	self = [super initWithGame:theGame Archer:theArcher Arrow:theArrow];
	powerInc = rand() % 20 +1;
	maxPower = rand() % 50 +1;
	maxAngle = -M_PI_4;
	angleInc = rand() % 2 +1;
	return self;
}

- (void) updateWithGameTime:(GameTime *)gameTime
{
	printf("%f", maxAngle);
	if(archer.power < maxPower)
		archer.power += gameTime.elapsedGameTime * powerInc;
	else if(archer.rotationAngle > maxAngle)
		archer.rotationAngle -= gameTime.elapsedGameTime * angleInc;
	else
	{
		arrow.rotationAngle = archer.rotationAngle;
		printf("%f", archer.rotationAngle);
		arrow.velocity.x = 50;
		arrow.velocity.y = -50;
		
		if([arrow.velocity length] > 50)
			[[arrow.velocity normalize] multiplyBy:50];
		[arrow.velocity multiplyBy:4];
		archer.launched = YES;
	}
}

@end
