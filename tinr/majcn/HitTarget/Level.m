//
//  Level.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Level.h"
#import "majcn.HitTarget.h"

@implementation Level

- (id) initWithGame:(Game *)theGame
{
	self = [super initWithGame:theGame];
	if (self != nil)
	{
		scene = [[Scene alloc] initWithGame:theGame];
		[self.game.components addComponent:scene];
		
		archer = [[Archer alloc] init];
		arrow = [[Arrow alloc] init];
		target = [[Target alloc] init];
		fire = [[Fire alloc] init];
		
		archer.fire = fire;
		archer.arrow = arrow;
	}
	return self;
}

@synthesize scene, archer, arrow, fire, target;

- (void) initialize
{
	[super initialize];
}

- (void) reset
{
	archer.position.x = 40;
	archer.position.y = 200;
	
	arrow.velocity.x = 0;
	arrow.velocity.y = 0;
	
	target.position.x = 160;
	target.position.y = 200;
	
	archer.power = 0;
	archer.launched = NO;
	archer.rotationAngle = 0;
	archer.arrow.rotationAngle = 0;
	
	archer.fire.position.x = 200;
	archer.fire.position.y = 200;
	
	[scene clear];
	
	[scene addItem:archer];
	[scene addItem:target];
}

- (void) dealloc
{
	[archer release];
	[arrow release];
	[fire release];
	[target release];
	
	[scene release];
	[super dealloc];
}


@end
