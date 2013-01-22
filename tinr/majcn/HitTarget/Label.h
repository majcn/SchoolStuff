//
//  Label.h
//  HitTarget
//
//  Created by Gregor Majcen on 1/4/11.
//  Copyright 2011 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"
#import "MirageEnums.h"

@interface Label : NSObject {
	SpriteFont *font;
	NSString *text;
	
	Color *color;
	
	Vector2 *position;
	Vector2 *origin;
	Vector2 *scale;
	float rotation;
	float layerDepth;
	
	HorizontalAlign horizontalAlign;
	VerticalAlign verticalAlign;
}

- (id) initWithFont:(SpriteFont*)theFont text:(NSString*)theText position:(Vector2*)thePosition;

@property (nonatomic, retain) SpriteFont *font;
@property (nonatomic, retain) NSString *text;

@property (nonatomic, retain) Color *color;

@property (nonatomic, retain) Vector2 *position;
@property (nonatomic, retain) Vector2 *origin;
@property (nonatomic, retain) Vector2 *scale;

@property (nonatomic) float rotation;
@property (nonatomic) float layerDepth;

@property (nonatomic) HorizontalAlign horizontalAlign;
@property (nonatomic) VerticalAlign verticalAlign;

- (void) setScaleUniform:(float)value;

@end
