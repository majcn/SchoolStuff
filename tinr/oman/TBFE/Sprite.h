//
//  Sprite.h
//  TBFE
//
//  Created by Marko Oman on 10/23/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Retronator.TBFE.classes.h"

@interface Sprite : NSObject {
	Texture2D *texture;
	Rectangle *sourceRectangle;
	Vector2 *origin;
	
	
}

@property (nonatomic, retain) Texture2D *texture;
@property (nonatomic, retain) Rectangle *sourceRectangle;
@property (nonatomic, retain) Vector2 *origin;

@end
