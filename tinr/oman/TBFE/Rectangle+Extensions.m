//
//  Rectangle+Extensions.m
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "Rectangle+Extensions.h"

@implementation Rectangle (Extensions)

- (BOOL) containsVector:(Vector2*) value {
	return (value.x >= data.x && value.x <= data.x + data.width &&
			value.y >= data.y && value.y <= data.y + data.height);
}

@end