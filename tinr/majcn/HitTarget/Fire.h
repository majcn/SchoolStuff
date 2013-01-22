//
//  Fire.h
//  HitTarget
//
//  Created by Gregor Majcen on 12/1/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import "IPosition.h"

@interface Fire : NSObject <IPosition>
{
	Vector2 *position;
}

@end
