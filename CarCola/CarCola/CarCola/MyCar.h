//
//  MyCar.h
//  CarCola
//
//  Created by Hao Zhou on 3/5/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface MyCar : NSObject
{
//    NSString *carMake;
//    NSString *carModel;
//    NSString *carTrim;
//    NSString *carYear;
//    NSString *carColor;
//    NSString *carNotes;
}

@property (nonatomic, retain)NSString *carMake;
@property (nonatomic, retain)NSString *carModel;
@property (nonatomic, retain)NSString *carTrim;
@property (nonatomic, retain)NSString *carYear;
@property (nonatomic, retain)NSString *carColor;
@property (nonatomic, retain)NSString *carNotes;
//@property (nonatomic, retain)NSString *carPhotoLink;

- (id)initWithMake: (NSString *)aCarMake
             model: (NSString *)aCarModel
              trim: (NSString *)aCarTrim
              year: (NSString *)aCarYear
             color: (NSString *)aCarColor
             notes: (NSString *)aNotes;
//             photo: (NSString *)aPhotoLink;

//-(NSString *)carMake;
//-(NSString *)carModel;
//-(NSString *)carTrim;
//-(NSString *)carYear;
//-(NSString *)carColor;
//-(NSString *)carNotes;


@end
