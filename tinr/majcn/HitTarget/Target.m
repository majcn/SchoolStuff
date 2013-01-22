//
//  Archer.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Target.h"
#import "majcn.HitTarget.h"

@implementation Target

- (id) init
{
	self = [super init];
	if (self != nil)
	{
		position = [[Vector2 alloc] init];
	}
	return self;
}

@synthesize position;

- (void) dealloc
{
	[position release];
	[super dealloc];
}


@end
