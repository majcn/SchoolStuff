//
//  Gameplay.m
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Gameplay.h"
#import "Retronator.TBFE.h"

@interface Gameplay ()

- (void) initWithGame:(Game *)theGame LevelClass:(Class)levelClass;

@end


@implementation Gameplay

- (id) initMultiplayerWithGame:(Game *)theGame levelClass:(Class)levelClass
{
	self = [super initWithGame:theGame];
	if (self != nil) {
		[self initWithGame:theGame LevelClass:levelClass];
		//self.updateOrder = 10;
		
		// Create players
		topPlayer = [[HumanPlayer alloc] initWithShip:level.topShip scene:level.scene position:PlayerPositionTop game:self.game];
		computerPlayer = [[Computer alloc] initWithEnemyShip:level.enemyship scene:level.scene position:PlayerPositionTop game:self.game];
		
		
		}
	return self;
}

- (id) initSinglePlayerWithGame:(Game *)theGame levelClass:(Class)levelClass aiClass:(Class)aiClass
{
	self = [super initWithGame:theGame];
	if (self != nil) {
		[self initWithGame:theGame LevelClass:levelClass];	
		
		// Create players
		topPlayer = [[HumanPlayer alloc] initWithShip:level.topShip scene:level.scene position:PlayerPositionTop game:self.game];	
		computerPlayer = [[Computer alloc] initWithEnemyShip:level.enemyship scene:level.scene position:PlayerPositionTop game:self.game];
	}
	return self;
}

- (void) initWithGame:(Game *)theGame LevelClass:(Class)levelClass
{
	// Allocate and initialize a new level and add it to components.
	level = [[levelClass alloc] initWithGame:self.game];
	[self.game.components addComponent:level];
	
	// Create a new renderer for the new scene and add it to components.
	renderer = [[Renderer alloc] initWithGame:self.game level:level];
	[self.game.components addComponent:renderer];
	
	
}

- (void) updateWithGameTime:(GameTime *)gameTime {
	[topPlayer updateWithGameTime:gameTime];
	//Novo
	[computerPlayer updateWithGameTime:gameTime];
	
		
		
}

- (void) dealloc
{
	[self.game.components removeComponent:level];
	[self.game.components removeComponent:renderer];
	[level release];
	[renderer release];
	[computerPlayer release];
	[topPlayer release];
	
	[super dealloc];
}



@end
