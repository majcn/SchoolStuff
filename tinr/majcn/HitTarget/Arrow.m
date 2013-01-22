//
//  Arrow.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Arrow.h"
#import "majcn.HitTarget.h"

@implementation Arrow

- (id) init
{
	self = [super init];
	if (self != nil)
	{
		position = [[Vector2 alloc] init];
		velocity = [[Vector2 alloc] init];
		rotationAngle = 0;
	}
	return self;
}

@synthesize position, velocity, rotationAngle;

- (void) dealloc
{
	[position release];
	[velocity release];
	[super dealloc];
}


@end
