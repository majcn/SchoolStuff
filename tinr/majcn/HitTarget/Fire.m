//
//  Fire.m
//  HitTarget
//
//  Created by Gregor Majcen on 12/1/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Fire.h"
#import "majcn.HitTarget.h"

@implementation Fire

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