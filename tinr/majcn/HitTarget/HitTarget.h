//
//  HitTarget.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

@interface HitTarget : Game
{	
	GraphicsDeviceManager *graphics;

	// Game state
	NSMutableArray *stateStack;
	
	// Progress
	GameProgress *progress;
}

@property (nonatomic, readonly) GameProgress *progress;

- (void) pushState:(GameState*)gameState;
- (void) popState;

@end
