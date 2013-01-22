//
//  GuiRenderer.h
//  friHockey
//
//  Created by Gregor Majcen on 1/4/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

@interface GuiRenderer : DrawableGameComponent
{
	SpriteBatch *spriteBatch;
	
	Scene *scene;
}

- (id) initWithGame:(Game*)theGame scene:(Scene*)theScene;

@end