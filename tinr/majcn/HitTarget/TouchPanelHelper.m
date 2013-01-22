//
//  TouchPanelHelper.m
//  Artificial I
//
//  Created by Matej Jan on 1.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import "TouchPanelHelper.h"

static TouchCollection *touches = nil;

@implementation TouchPanelHelper

+ (void) initialize {
	touches = nil;
}

+ (TouchCollection *) getState {
	return touches;
}

- (void) updateWithGameTime:(GameTime *)gameTime {
	[touches release];
	touches = [[TouchPanel getState] retain];
}

- (void) dealloc
{
	[touches release];
	[super dealloc];
}


@end
