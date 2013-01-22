//
//  TBFE.m
//  TBFE
//
//  Created by Marko Oman on 10/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "TBFE.h"
#import "Retronator.TBFE.h"

@implementation TBFE

- (id) init {
    if (self = [super init]) {
        graphics = [[GraphicsDeviceManager alloc] initWithGame:self];
    }
    return self;
}

- (void) initialize {
	// Add all level classes.
	levelClasses = [[NSMutableArray alloc] init];
	[levelClasses addObject:[TBFELevel class]];
	
	// Start in first level.
	[self loadMultiplayerLevel:[levelClasses objectAtIndex:0]];
	
	// Initialize all components.
	[super initialize];
}

- (void) loadMultiplayerLevel:(Class)levelClass {	
	// Unload the current gameplay.
	if (currentGameplay) {
		[self.components removeComponent:currentGameplay];
		[currentGameplay release];
	}
	
	// Allocate and initialize new gameplay object and add it to components.
	currentGameplay = [[Gameplay alloc] initMultiplayerWithGame:self levelClass:levelClass];
	[self.components addComponent:currentGameplay];
}

- (void) dealloc
{
	[levelClasses release];
    [graphics release];
    [super dealloc];
}

@end
