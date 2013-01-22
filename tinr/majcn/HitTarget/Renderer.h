//
//  Renderer.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import "Retronator.Xni.Framework.Graphics.h"
#import "Retronator.Xni.Framework.Content.h"

@interface Renderer : DrawableGameComponent
{
	SpriteBatch *spriteBatch;
	Texture2D *atlas;
	
	Sprite *archerTeloSprite, *archerLokSprite, *archerRokaSprite;
	Vector2 *archerLokPosOffset, *archerRokaPosOffset;

	Sprite *arrowSprite;
	Vector2 *arrowPosOffset;
	
	Sprite *fireSprite;
	
	Gameplay *gameplay;
}

- (id) initWithGame:(Game*)theGame gameplay:(Gameplay*)theGameplay;

@end
