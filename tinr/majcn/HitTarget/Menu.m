//
//  Menu.m
//  friHockey
//
//  Created by Matej Jan on 22.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import "Menu.h"

#import "Retronator.Xni.Framework.Content.h"

#import "Retronator.Xni.Framework.Content.Pipeline.Processors.h"
#import "majcn.HitTarget.h"

@implementation Menu

- (id) initWithGame:(Game *)theGame
{
	self = [super initWithGame:theGame];
	if (self != nil) {
		scene = [[Scene alloc] initWithGame:self.game];		
		renderer = [[GuiRenderer alloc] initWithGame:self.game scene:scene];
	}
	return self;
}

- (void) activate {
	[self.game.components addComponent:scene];
	[self.game.components addComponent:renderer];	
}

- (void) deactivate {
	[self.game.components removeComponent:scene];
	[self.game.components removeComponent:renderer];	
}

- (void) initialize {
	// Fonts
	FontTextureProcessor *fontProcessor = [[[FontTextureProcessor alloc] init] autorelease];
	font = [[self.game.content load:@"font" processor:fontProcessor] autorelease];
	
	back = [[Button alloc] initWithInputArea:[Rectangle rectangleWithX:0 y:280 width:150 height:32]
								  background:nil font:font text:@"Exit"];
	[scene addItem:back];

	[super initialize];
}

- (void) updateWithGameTime:(GameTime *)gameTime {
	// Update all buttons.
	for (id item in scene) {
		Button *button = [item isKindOfClass:[Button class]] ? item : nil;
		
		if (button) {
			[button update];
		}
	}	
	
	if (back.wasReleased) {
		[hitTarget popState];
	}
}

- (void) dealloc
{
	[back release];
	[font release];
	[scene release];
	[renderer release];	 
	[super dealloc];
}

@end
