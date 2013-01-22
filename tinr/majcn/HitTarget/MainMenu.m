//
//  MainMenu.m
//  friHockey
//
//  Created by Matej Jan on 21.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import "MainMenu.h"

#import "Retronator.Xni.Framework.Content.h"

#import "majcn.HitTarget.h"

@implementation MainMenu

- (void) initialize {
	[super initialize];
	
	// Background
	Texture2D *tableTexture = [[self.game.content load:@"TablePlain"] autorelease];	
	background = [[Image alloc] initWithTexture:tableTexture position:[Vector2 vectorWithX:-100 y:0]];	
	[background setScaleUniform: 2];
	[scene addItem:background];
	
	// Text
	title = [[Label alloc] initWithFont:font text:@"hitTarget" position:[Vector2 vectorWithX:0 y:10]];
	title.horizontalAlign = HorizontalAlignLeft;
	[scene addItem:title];
	
	subtitle = [[Label alloc] initWithFont:font text:@"     by majcn" position:[Vector2 vectorWithX:0 y:50]];
	subtitle.horizontalAlign = HorizontalAlignLeft;
	[scene addItem:subtitle];
	
	// Buttons
	singleplayer = [[Button alloc] initWithInputArea:[Rectangle rectangleWithX:200 y:140 width:140 height:32] 
										  background:nil font:font text:@"PLAY"];
	[singleplayer.label setScaleUniform:2];
	[scene addItem:singleplayer];
	
	topScore = [[Button alloc] initWithInputArea:[Rectangle rectangleWithX:200 y:180 width:140 height:32] 
										  background:nil font:font text:@"Score"];
	[scene addItem:topScore];
	
	about = [[Button alloc] initWithInputArea:[Rectangle rectangleWithX:200 y:200 width:140 height:32] 
									  background:nil font:font text:@"About"];
	[scene addItem:about];
}

- (void) updateWithGameTime:(GameTime *)gameTime {
	[super updateWithGameTime:gameTime];
	
	GameState *newState = nil;
	
	if (singleplayer.wasReleased) {
		newState = [[[Gameplay alloc] initWithGame:self.game] autorelease];
	} else if (topScore.wasReleased) {	
		newState = [[[TopScore alloc] initWithGame:self.game] autorelease];
	} else if (about.wasReleased) {	
		newState = [[[About alloc] initWithGame:self.game] autorelease];
	}
					
	if (newState) {
		[hitTarget pushState:newState];
	}
}


- (void) dealloc
{
	[background release];
	[title release];
	[subtitle release];
	[singleplayer release];
	
	[super dealloc];
}


@end
