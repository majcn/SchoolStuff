//
//  GameHud.m
//  HitTarget
//
//  Created by Gregor Majcen on 1/4/11.
//  Copyright 2011 majcn. All rights reserved.
//

#import "GameHud.h"

#import "majcn.HitTarget.h"
#import "Retronator.Xni.Framework.Graphics.h"
#import "Retronator.Xni.Framework.Content.Pipeline.Processors.h"

@implementation GameHud

- (id) initWithGame:(Game *)theGame
{
	self = [super initWithGame:theGame];
	if (self != nil) {
		scene = [[Scene alloc] initWithGame:self.game];
		[self.game.components addComponent:scene];		
	}
	return self;
}

@synthesize scene;

- (void) initialize {
	FontTextureProcessor *fontProcessor = [[[FontTextureProcessor alloc] init] autorelease];
	SpriteFont *font = [[self.game.content load:@"font" processor:fontProcessor] autorelease];
	
	plyerShots = [[Label alloc] initWithFont:font text:@"0" position:[Vector2 vectorWithX:0 y:0]];
	
	[scene addItem:plyerShots];
}

- (void) changePlayerShots:(int)value
{
	plyerShots.text = [NSString stringWithFormat:@"Shots: %i", value];
	[plyerShots setColor:[Color gray]];
	[plyerShots setScaleUniform:2];
}

- (void) dealloc
{
	[super dealloc];
}

@end