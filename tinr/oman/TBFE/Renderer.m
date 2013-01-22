//
//  Renderer.m
//  TBFE
//
//  Created by Marko Oman on 10/23/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Renderer.h"
#import "Scene.h"
#import "Retronator.TBFE.h"

@implementation Renderer

-(id) initWithGame:(Game *)theGame level:(Level *)theLevel{
	if(self = [super initWithGame:theGame]){
		level = theLevel;
		content = [[ContentManager alloc] initWithServiceProvider:self.game.services];
		lightPosition = [[Vector2 alloc] initWithX:160 y:230];

	}
	return self;
}

-(void) initialize{
	[super initialize];
}

-(void) loadContent{
	spriteBatch = [[SpriteBatch alloc]initWithGraphicsDevice:self.graphicsDevice];
	
	shipSprite = [[Sprite alloc]init];
	shipSprite.texture = [self.game.content load:@"Slika"];
	shipSprite.sourceRectangle = [Rectangle rectangleWithX:15 y:5 width:85 height:90];
	shipSprite.origin = [Vector2 vectorWithX:40 y:45];
	
	enemyShipSprite = [[Sprite alloc]init];
	enemyShipSprite.texture = [self.game.content load:@"Slika"];
	enemyShipSprite.sourceRectangle = [Rectangle rectangleWithX:20 y:140 width:85 height:85];
	enemyShipSprite.origin = [Vector2 vectorWithX:0 y:0];
	
	enemyShipSprite1 =[[Sprite alloc]init];
	enemyShipSprite1.texture = [self.game.content load:@"Slika"];
	enemyShipSprite1.sourceRectangle = [Rectangle rectangleWithX:140 y:34 width:95 height:60];
	enemyShipSprite1.origin = [Vector2 vectorWithX:0 y:0];
	
	/*bulletSprite =[[Sprite alloc]init];
	bulletSprite.texture = [self.game.content load:@"Slika"];
	bulletSprite.sourceRectangle = [Rectangle rectangleWithX:220 y:151 width:10 height:10];
	bulletSprite.origin = [Vector2 vectorWithX:0 y:0];
	
	bulletSprite1 =[[Sprite alloc]init];
	bulletSprite1.texture = [self.game.content load:@"Slika"];
	bulletSprite1.sourceRectangle = [Rectangle rectangleWithX:180 y:151 width:10 height:10];
	bulletSprite1.origin = [Vector2 vectorWithX:0 y:0];
	*/
}

-(void) drawWithGameTime:(GameTime *)gameTime{
	[self.graphicsDevice clearWithColor:[Color gray]];
	
	[spriteBatch beginWithSortMode:SpriteSortModeBackToFront BlendState:nil];

	[spriteBatch draw:background to:[Vector2 vectorWithX:0 y:-20] fromRectangle:nil tintWithColor:[Color white]
			 rotation:0 origin:[Vector2 zero] scaleUniform:1 effects:SpriteEffectsNone layerDepth:0.9];
	
	
		for (id item in level.scene) {
			id<IPosition> itemWithPosition = [item conformsToProtocol:@protocol(IPosition)] ? item : nil;
			
			
			Sprite *sprite = nil;	
			SpriteEffects effects = SpriteEffectsNone;
			
			
			
				if([item isKindOfClass:[Ship class]]){
					sprite = shipSprite;
					//if (ship.position.y > 280) {
					//	effects = SpriteEffectsFlipVertically;
					//}
				}
				if([item isKindOfClass:[EnemyShip1 class]]){
					sprite = enemyShipSprite1;
				}
				/*if([item isKindOfClass:[Bullet class]]){
					sprite = bulletSprite;
				}
				if([item isKindOfClass:[Bullet1 class]]){
					sprite = bulletSprite1;
				}*/
				 else if([item isKindOfClass:[EnemyShip class]]){
					sprite = enemyShipSprite;
				}
			
			
				
				if(itemWithPosition && sprite){
					[spriteBatch draw:sprite.texture
								   to:itemWithPosition.position
						fromRectangle:sprite.sourceRectangle
						 tintWithColor:[Color white]
							 rotation:0
							   origin:sprite.origin
						 scaleUniform:1
							  effects:effects
						   layerDepth:0.1];
				}
		}
				[spriteBatch end];
}

-(void) unloadContent{
	[content unload];
}

- (void) dealloc
{
	[content release];
	[spriteBatch release];
	[super dealloc];
}

@end
