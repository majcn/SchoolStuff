//
//  Sprite.m
//  TBFE
//
//  Created by Marko Oman on 10/23/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Sprite.h"
#import "Retronator.TBFE.h"

@implementation Sprite

@synthesize texture;
@synthesize sourceRectangle;
@synthesize origin;

- (void) dealloc
{
	[texture release];
	[sourceRectangle release];
	[origin release];
	[super dealloc];
}

@end
