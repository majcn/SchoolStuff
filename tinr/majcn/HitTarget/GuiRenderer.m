//
//  GuiRenderer.m
//  friHockey
//
//  Created by Gregor Majcen on 1/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "GuiRenderer.h"
#import "majcn.HitTarget.h"

@implementation GuiRenderer

- (id) initWithGame:(Game*)theGame scene:(Scene*)theScene
{
	self = [super initWithGame:theGame];
	if (self != nil) {
		scene = theScene;
	}
	return self;
}

- (void) loadContent {
	spriteBatch = [[SpriteBatch alloc] initWithGraphicsDevice:self.graphicsDevice];
}

- (void) drawWithGameTime:(GameTime *)gameTime {
	[spriteBatch beginWithSortMode:SpriteSortModeDeffered
						BlendState:nil 
					  SamplerState:[SamplerState pointClamp]
				 DepthStencilState:nil 
				   RasterizerState:nil];
	
	for (id item in scene) {
		Label *label = [item isKindOfClass:[Label class]] ? item : nil;
		
		if (label) {
			[spriteBatch drawStringWithSpriteFont:label.font text:label.text to:label.position tintWithColor:label.color
										 rotation:label.rotation origin:label.origin scale:label.scale effects:SpriteEffectsNone layerDepth:label.layerDepth];
		}
	}
	
	[spriteBatch end];
}

- (void) unloadContent
{
	[spriteBatch release];
}

@end
