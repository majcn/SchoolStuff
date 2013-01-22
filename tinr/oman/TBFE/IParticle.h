//
//  IParticle.h
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "IMovable.h"
#import "IMass.h"
#import "IParticleCollider.h"


@protocol IParticle<IMovable, IMass, IParticleCollider>


@end
