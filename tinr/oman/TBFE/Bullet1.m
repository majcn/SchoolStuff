//
//  Bullet1.m
//  TBFE
//
//  Created by Marko Oman on 10/25/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Bullet1.h"

#import "Retronator.TBFE.h"

@implementation Bullet1

- (id) init
{
	self = [super init];
	if (self != nil) {
		position = [[Vector2 alloc] init];
		velocity = [[Vector2 alloc] init];
		mass = 20;
		radius = 30;
	}
	return self;
}


@synthesize position, velocity, mass, radius;

- (void) dealloc
{
	[velocity release];
	[position release];
	[super dealloc];
}


@end