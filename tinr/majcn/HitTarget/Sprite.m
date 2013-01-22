//
//  Sprite.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Sprite.h"
#import "majcn.HitTarget.h"

@implementation Sprite

@synthesize texture, sourceRectangle, origin;

- (void) dealloc
{
	[texture release];
	[sourceRectangle release];
	[origin release];
	[super dealloc];
}


@end
