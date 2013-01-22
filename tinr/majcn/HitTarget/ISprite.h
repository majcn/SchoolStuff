//
//  ISprite.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol ISprite <NSObject>

@property (nonatomic, retain) Texture2D *texture;
@property (nonatomic, retain) Rectangle *sourceRectangle;
@property (nonatomic, retain) Vector2 *origin;

@end
