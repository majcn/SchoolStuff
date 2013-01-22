//
//  Gameplay.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Gameplay.h"
#import "majcn.HitTarget.h"
//#import "CommonFunc.h"

@implementation Gameplay

- (id) initWithGame:(Game *)theGame
{
	self = [super initWithGame:theGame];
	if (self != nil)
	{
		
		//shots = [CommonFunc getShots];		
		shots = [hitTarget.progress getScore];
		
		level = [[Level alloc] initWithGame:self.game];
		music = [theGame.content load:@"music"];
		
		player = [[HumanPlayer alloc] initWithGame:self.game Archer:level.archer Arrow:level.arrow];
		physics = [[Physics alloc] initWithGame:self.game level:level];
		renderer = [[Renderer alloc] initWithGame:self.game gameplay:self];
		
		hud = [[GameHud alloc] initWithGame:self.game];
		hudRenderer = [[GuiRenderer alloc] initWithGame:self.game scene:hud.scene];
		hudRenderer.drawOrder = 1;
		
		player.updateOrder = 0;		// First the game should process input.		
		physics.updateOrder = 1;	// Then the physics engine updates the world.
		level.updateOrder = 2;		// Level updates the scene.
		level.scene.updateOrder = 3;
		self.updateOrder = 4;		// At last gameplay rules are executed.
	}
	return self;
}

@synthesize level;

- (void) initialize
{
	[self reset];
	[super initialize];
}

- (void) updateWithGameTime:(GameTime *)gameTime
{
	if(level.archer.arrow.position.y >= 320)
	{
		//level.archer.arrow.velocity.x = 0;
		//level.archer.arrow.velocity.y = 0;
		[self reset];
	}
}

- (void) activate {
	[self.game.components addComponent:level];
	[self.game.components addComponent:player];
	[self.game.components addComponent:physics];
	[self.game.components addComponent:renderer];
	[self.game.components addComponent:hud];
	[self.game.components addComponent:hudRenderer];	
}

- (void) deactivate {
	[self.game.components removeComponent:level];
	[self.game.components removeComponent:player];
	[self.game.components removeComponent:physics];
	[self.game.components removeComponent:renderer];
	[self.game.components removeComponent:hud];
	[self.game.components removeComponent:hudRenderer];
}

- (void) reset
{
	[hud changePlayerShots:shots++];
	[hitTarget.progress incScore];
	[music play];
	[level reset];
}

- (void) dealloc
{	
	[level release];
	[player release];
	[physics release];
	[renderer release];
	[super dealloc];
}



@end
