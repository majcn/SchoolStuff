//
//  Renderer.h
//  TBFE
//
//  Created by Marko Oman on 10/23/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


#import "Retronator.Xni.Framework.Content.h"
#import "Retronator.TBFE.h"
#import "Retronator.TBFE.classes.h"

@interface Renderer : DrawableGameComponent {
	ContentManager *content;
	Sprite *shipSprite;
	Sprite *enemyShipSprite;
	Sprite *enemyShipSprite1;
	//Sprite *bulletSprite;
	//Sprite *bulletSprite1;
	
	Texture2D *background;
	Vector2 *lightPosition;
	
	SpriteBatch *spriteBatch;
	
	Level *level;
	
}

-(id) initWithGame:(Game *)theGame level:(Level*)theLevel;

@end
