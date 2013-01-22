//
//  Sprite.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import "ISprite.h"

@interface Sprite : NSObject <ISprite>
{
	Texture2D *texture;
	Rectangle *sourceRectangle;
	Vector2 *origin;
}

@end
