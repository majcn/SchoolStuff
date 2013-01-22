//
//  Archer.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Archer.h"
#import "majcn.HitTarget.h"

@implementation Archer

- (id) init
{
	self = [super init];
	if (self != nil)
	{
		position = [[Vector2 alloc] init];
		rotationAngle = 0;
		
		launched = NO;
		power = 1;
	}
	return self;
}

@synthesize position, rotationAngle, arrow, fire;
@synthesize launched, power;

- (void) dealloc
{
	[position release];
	[super dealloc];
}


@end
