//
//  Renderer.m
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import "Renderer.h"
#import "majcn.HitTarget.h"

@implementation Renderer

- (id) initWithGame:(Game *)theGame gameplay:(Gameplay*)theGameplay
{
	if (self = [super initWithGame:theGame])
	{
		gameplay = theGameplay;
	}
	return self;
}

- (void) initialize
{
	[super initialize];
}

- (void) loadContent
{
	spriteBatch = [[SpriteBatch alloc] initWithGraphicsDevice:self.graphicsDevice];
	atlas = [self.game.content load:@"atlas"];
	
	archerTeloSprite = [[Sprite alloc] init];
	archerTeloSprite.texture = atlas;
	archerTeloSprite.sourceRectangle = [Rectangle rectangleWithX:0 y:0 width:72 height:206];
	archerTeloSprite.origin = [Vector2 vectorWithX:0 y:0];

	archerLokSprite = [[Sprite alloc] init];
	archerLokSprite.texture = atlas;
	archerLokSprite.sourceRectangle = [Rectangle rectangleWithX:128 y:0 width:78 height:178];
	archerLokSprite.origin = [Vector2 vectorWithX:0 y:102];
	archerLokPosOffset = [[Vector2 alloc] initWithX:60 y:50];
	
	archerRokaSprite = [[Sprite alloc] init];
	archerRokaSprite.texture = atlas;
	archerRokaSprite.sourceRectangle = [Rectangle rectangleWithX:226 y:0 width:54 height:14];
	archerRokaSprite.origin = [Vector2 vectorWithX:-10 y:13];
	archerRokaPosOffset = [[Vector2 alloc] initWithX:0 y:0];
	
	arrowSprite = [[Sprite alloc] init];
	arrowSprite.texture = atlas;
	arrowSprite.sourceRectangle = [Rectangle rectangleWithX:226 y:30 width:80 height:8];
	arrowSprite.origin = [Vector2 vectorWithX:80 y:10];
	arrowPosOffset = [[Vector2 alloc] initWithX:0 y:0];
	
	fireSprite = [[Sprite alloc] init];
	fireSprite.texture = atlas;
	fireSprite.sourceRectangle = [Rectangle rectangleWithX:226 y:160 width:80 height:27];
	fireSprite.origin = [Vector2 vectorWithX:80 y:14];
}

- (void) drawWithGameTime:(GameTime *)gameTime
{
	[self.graphicsDevice clearWithColor:[Color whiteSmoke]];
	
	[spriteBatch beginWithSortMode:SpriteSortModeBackToFront BlendState:nil];
	//[spriteBatch beginWithSortMode:SpriteSortModeBackToFront BlendState:nil SamplerState:nil DepthStencilState:nil RasterizerState:nil Effect:nil TransformMatrix:[Matrix createScaleUniform:1]];
	
	for(id item in gameplay.level.scene)
	{
		SpriteEffects effects = SpriteEffectsNone;
		Sprite *sprite;
		if ([item isKindOfClass:[Archer class]])
		{
			Vector2 *posOffset;
			Archer *archer = (Archer*)item;
		
			sprite = archerTeloSprite;
			[spriteBatch draw:sprite.texture to:archer.position fromRectangle:sprite.sourceRectangle tintWithColor:[Color white] rotation:0 origin:sprite.origin scaleUniform:1 effects:effects layerDepth:0];
			
			sprite = archerLokSprite;
			posOffset = archerLokPosOffset;
			[spriteBatch draw:sprite.texture to:[Vector2 add:archer.position to:posOffset] fromRectangle:sprite.sourceRectangle tintWithColor:[Color white] rotation:archer.rotationAngle origin:sprite.origin scaleUniform:1 effects:effects layerDepth:0];

			sprite = archerRokaSprite;
			posOffset = archerRokaPosOffset;
			posOffset.x = archerLokPosOffset.x - cosf(archer.rotationAngle) * archer.power;
			posOffset.y = archerLokPosOffset.y - sinf(archer.rotationAngle) * archer.power;
			[spriteBatch draw:sprite.texture to:[Vector2 add:archer.position to:posOffset] fromRectangle:sprite.sourceRectangle tintWithColor:[Color white] rotation:archer.rotationAngle origin:sprite.origin scaleUniform:1 effects:effects layerDepth:0];
		
			sprite = arrowSprite;
			posOffset = arrowPosOffset;
			if(!archer.launched)
			{
				posOffset.x = archerRokaPosOffset.x + cosf(archer.rotationAngle) * 140;
				posOffset.y = archerRokaPosOffset.y + sinf(archer.rotationAngle) * 140;
				archer.arrow.position = [Vector2 add:archer.position to:posOffset];
				[spriteBatch draw:sprite.texture to:archer.arrow.position fromRectangle:sprite.sourceRectangle tintWithColor:[Color white] rotation:archer.rotationAngle origin:sprite.origin scaleUniform:1 effects:effects layerDepth:0];
				sprite = fireSprite;
				[spriteBatch draw:sprite.texture to:archer.fire.position fromRectangle:sprite.sourceRectangle tintWithColor:[Color white] rotation:archer.rotationAngle origin:sprite.origin scaleUniform:archer.power/50 effects:effects layerDepth:0];
			}
			else
			{
				[spriteBatch draw:sprite.texture to:archer.arrow.position fromRectangle:sprite.sourceRectangle tintWithColor:[Color white] rotation:archer.arrow.rotationAngle origin:sprite.origin scaleUniform:1 effects:effects layerDepth:0];
			}
		}
		else if ([item isKindOfClass:[Arrow class]])
		{
			//Arrow *arrow = (Arrow*)item;
			
			//sprite = arrowSprite;
			//[spriteBatch draw:sprite.texture to:arrow.position fromRectangle:sprite.sourceRectangle tintWithColor:[Color white] rotation:arrow.rotationAngle origin:sprite.origin scaleUniform:1 effects:effects layerDepth:0];
		}
	}
	
	[spriteBatch end];
}

- (void) unloadContent
{
	[spriteBatch release];
	[atlas release];
	[archerTeloSprite release];
	[archerLokSprite release];
	[archerLokPosOffset release];
	[archerRokaSprite release];
	[archerRokaPosOffset release];
	[arrowSprite release];
	[arrowPosOffset release];
}

- (void) dealloc
{
	[super dealloc];
}

@end
