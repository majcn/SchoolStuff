//
//  HitTarget.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "HitTarget.h"
#import "majcn.HitTarget.h"
#import "MainMenu.h"
//#import "CommonFunc.h"

@implementation HitTarget

- (id) init
{
	self = [super init];
	if (self != nil)
	{
		graphics = [[GraphicsDeviceManager alloc] initWithGame:self];
		progress = [[GameProgress loadProgress] retain];
		[self.components addComponent:[[[TouchPanelHelper alloc] initWithGame:self] autorelease]];
		
		stateStack = [[NSMutableArray alloc] init];
	}
	return self;
}

@synthesize progress;

- (void) initialize
{
	// Start in main menu.
	MainMenu *mainMenu = [[[MainMenu alloc] initWithGame:self] autorelease];
	[self pushState:mainMenu];
	[super initialize];
}

- (void) pushState:(GameState *)gameState {
	GameState *currentActiveState = [stateStack lastObject];
	[currentActiveState deactivate];
	[self.components removeComponent:currentActiveState];
	
	[stateStack addObject:gameState];
	[self.components addComponent:gameState];
	[gameState activate];
}

- (void) popState {
	GameState *currentActiveState = [stateStack lastObject];
	[stateStack removeLastObject];
	[currentActiveState deactivate];
	[self.components removeComponent:currentActiveState];
	
	currentActiveState = [stateStack lastObject];
	[self.components addComponent:currentActiveState];	
	[currentActiveState activate];
}

- (void) updateWithGameTime:(GameTime *)gameTime {
	[super updateWithGameTime:gameTime];
}

- (void) drawWithGameTime:(GameTime *)gameTime {
	[self.graphicsDevice clearWithColor:[Color black]];
	[super drawWithGameTime:gameTime];
}

- (void) dealloc
{
	[stateStack release];
    [graphics release];
	[progress release];
    [super dealloc];
}

@end
